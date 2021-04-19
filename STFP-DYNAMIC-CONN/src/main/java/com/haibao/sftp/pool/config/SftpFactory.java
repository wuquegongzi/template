package com.haibao.sftp.pool.config;

import com.haibao.sftp.pool.exception.SftpPoolException;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/*
 * @Author ml.c
 * @Description //sftp连接工厂
 * @Date 5:39 PM 1/14/21
 **/
@Data
@Slf4j
public class SftpFactory extends BasePooledObjectFactory<ChannelSftp> {

    private static final String CHANNEL_TYPE = "sftp";
    private static Properties sshConfig = new Properties();

    static {
        sshConfig.put("StrictHostKeyChecking", "no");
    }

    private SftpProperties properties;

    public SftpFactory(SftpProperties properties) {
        this.properties = properties;
    }

    @Override
    public ChannelSftp create() {
        ChannelSftp channel = null;
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(properties.getUsername(), properties.getHost(), properties.getPort());
            sshSession.setPassword(properties.getPassword());

            sshSession.setConfig(sshConfig);
            sshSession.connect();
            channel = (ChannelSftp) sshSession.openChannel(CHANNEL_TYPE);
            channel.connect();

        } catch (JSchException e) {
            throw new SftpPoolException("连接sftp失败:"+properties.getHost(), e);
        }

        return channel;
    }

    @Override
    public PooledObject<ChannelSftp> wrap(ChannelSftp channelSftp) {
        return new DefaultPooledObject<>(channelSftp);
    }

    /**
     * 销毁对象
      */
    @Override
    public void destroyObject(PooledObject<ChannelSftp> p) {
        if(null != p){
            ChannelSftp channelSftp = p.getObject();
            if(null != channelSftp){
                channelSftp.disconnect();
            }

        }
    }
}
