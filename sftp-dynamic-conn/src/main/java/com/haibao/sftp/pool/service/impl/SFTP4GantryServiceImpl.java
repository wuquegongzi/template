package com.haibao.sftp.pool.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.haibao.sftp.pojo.ParmsQuery;
import com.haibao.sftp.pool.config.SftpHelper;
import com.haibao.sftp.pool.config.SftpProperties;
import com.haibao.sftp.pool.service.SFTPService;
import com.haibao.sftp.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

/*
 * @Author ml.c
 * @Description
 * @Date 1:43 PM 1/14/21
 **/
@Service
public class SFTP4GantryServiceImpl implements SFTPService {

    private final static Logger logger = LoggerFactory.getLogger(SFTP4GantryServiceImpl.class);

    @Autowired
    SftpHelper sftpHelper;
    @Autowired
    SftpProperties sftpProperties;

    /**
     * sftp获取图片实现
     * @param parmsQuery
     * @return
     */
    @Override
    public ResponseEntity getSftpPicStream(ParmsQuery parmsQuery) {

//        SftpProperties sftpProperties = SpringContextUtil.getBean(SftpProperties.class);

        String filePath = parmsQuery.getPath();
        String sftpHost = parmsQuery.getSftpHost();
        int sftpPort = parmsQuery.getSftpPort();
        String sftpUsername = parmsQuery.getSftpUsername();
        String sftpPasswd = parmsQuery.getSftpPassword();

        //如果使用动态连接，替换到默认配置
        SftpProperties newSftpProperties = new SftpProperties();
        CglibUtil.copy(sftpProperties,newSftpProperties);
        if(StrUtil.isNotEmpty(sftpHost)){
            newSftpProperties.setHost(sftpHost);
        }
        if(StrUtil.isNotEmpty(sftpUsername)){
            newSftpProperties.setUsername(sftpUsername);
        }
        if(StrUtil.isNotEmpty(sftpPasswd)){
            newSftpProperties.setPassword(sftpPasswd);
        }
        if(sftpPort > 0){
            newSftpProperties.setPort(sftpPort);
        }

        String fileName = FileUtil.getName(filePath);
        String fileFolder = FileUtil.getParent(filePath,1);

        String outstr = null;
        try {
            logger.info("sftpHost:{},fileFolder:{},fileName:{}",sftpHost,fileFolder,fileName);
            byte[] bytes = sftpHelper.download(newSftpProperties,fileFolder,fileName);
            if(null != bytes && bytes.length > 0){
                BASE64Encoder encoder = new BASE64Encoder();
                outstr = encoder.encode(bytes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null != outstr){
            return ResponseEntity.ok(outstr);
        }
        return ResponseEntity.status(-999).body("获取sftp文件失败！");
    }

}
