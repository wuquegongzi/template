package com.haibao.sftp.pool.config;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @Author ml.c
 * @Description //
 * @Date 4:56 PM 1/14/21
 **/
@Data
@Component
@ConfigurationProperties(prefix = "sftp")
public class SftpProperties{

    @Value("${sftp.host}")
    private String host;
    @Value("${sftp.port}")
    private int port;
    @Value("${sftp.username}")
    private String username;
    @Value("${sftp.password}")
    private String password;

    @Value("${sftp.pool.max-total}")
    private int maxTotal;
    @Value("${sftp.pool.max-idle}")
    private int maxIdle;
    @Value("${sftp.pool.min-idle}")
    private int minIdle;

    private Pool pool = new Pool(maxTotal,maxIdle,minIdle);

    public static class Pool extends GenericObjectPoolConfig<ChannelSftp> {

        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public Pool(int maxTotal,int maxIdle,int minIdle ) {

            super();
            if(maxTotal > 0){
                this.maxTotal = maxTotal;
            }
            if(maxIdle > 0){
                this.maxIdle = maxIdle;
            }

            if(minIdle > 0){
                this.minIdle = minIdle;
            }
        }

        @Override
        public int getMaxTotal() {
            return maxTotal;
        }
        @Override
        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }
        @Override
        public int getMaxIdle() {
            return maxIdle;
        }
        @Override
        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }
        @Override
        public int getMinIdle() {
            return minIdle;
        }
        @Override
        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

    }
}
