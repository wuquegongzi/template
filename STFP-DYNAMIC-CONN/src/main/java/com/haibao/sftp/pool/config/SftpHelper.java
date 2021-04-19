package com.haibao.sftp.pool.config;

import com.haibao.sftp.utils.ByteUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * @Author ml.c
 * @Description //sftp辅助类
 * @Date 5:47 PM 1/14/21
 **/
@Component
public class SftpHelper {

    private final static Logger logger = LoggerFactory.getLogger(SftpHelper.class);

    private static ConcurrentHashMap<SftpProperties, SftpPool> poolMap = new ConcurrentHashMap();

    private SftpPool getSftpPool(SftpProperties sftpProperties) {

        SftpPool pool = poolMap.get(sftpProperties);

        if(null == pool){
            synchronized(this){
                if(null == pool){
                    SftpFactory sftpFactory = new SftpFactory(sftpProperties);
                    pool  = new SftpPool(sftpFactory);
                    logger.info("创建新的sftp连接池:{}", sftpProperties.getHost()+"_"+sftpProperties.getUsername());
                    poolMap.put(sftpProperties,pool);
                }
            }
        }

        return pool;
    }

    /**
     * 下载文件
     * @param dir 远程目录
     * @param name 远程文件名
     * @return 文件字节数组
     */
    public byte[] download(SftpProperties sftpProperties,String dir, String name) throws Exception {

        SftpPool pool = getSftpPool(sftpProperties);
        if(null == pool){
            throw new Exception("获取sftp连接池失败！"+sftpProperties.getHost());
        }

        ChannelSftp sftp = pool.borrowObject();
        System.out.println("download:"+dir+"-"+name+"-session:"+sftp.getSession());
        try {
            sftp.cd(dir);
            InputStream in = sftp.get(name);
            return ByteUtil.inputStreamToByteArray(in);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            pool.returnObject(sftp);
        }

        return null;
    }

    /**
     * 上传文件
     * @param dir 远程目录
     * @param name 远程文件名
     * @param in 输入流
     */
    public void upload(SftpProperties sftpProperties,String dir, String name, InputStream in) throws Exception {

        SftpPool pool = getSftpPool(sftpProperties);
        if(null == pool){
            throw new Exception("获取sftp连接池失败！"+sftpProperties.getHost());
        }

        ChannelSftp sftp = pool.borrowObject();
        try {
            mkdirs(sftp, dir);
            sftp.cd(dir);
            sftp.put(in, name);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            pool.returnObject(sftp);
        }
    }

    /**
     * 删除文件
     * @param dir 远程目录
     * @param name 远程文件名
     */
    public void delete(SftpProperties sftpProperties,String dir, String name) throws Exception {

        SftpPool pool = getSftpPool(sftpProperties);
        if(null == pool){
            throw new Exception("获取sftp连接池失败！"+sftpProperties.getHost());
        }

        ChannelSftp sftp = pool.borrowObject();
        try {
            sftp.cd(dir);
            sftp.rm(name);
        } catch (SftpException e) {
           e.printStackTrace();
        } finally {
            pool.returnObject(sftp);
        }
    }

    /**
     * 递归创建多级目录
     * @param dir 多级目录
     */
    private void mkdirs(ChannelSftp sftp, String dir) {
        String[] folders = dir.split("/");
        try {
            sftp.cd("/");
            for (String folder: folders) {
                if (folder.length()>0) {
                    try {
                        sftp.cd(folder);
                    } catch (Exception e) {
                        sftp.mkdir(folder);
                        sftp.cd(folder);
                    }
                }
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}
