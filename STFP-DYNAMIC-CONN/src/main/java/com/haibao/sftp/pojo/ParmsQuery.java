package com.haibao.sftp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author ml.c
 * @date 3:18 PM 4/19/21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParmsQuery {

    /**
     * 文件路径
     */
    private String path;

    /**
     * sftp server地址
     */
    private String sftpHost;

    /**
     * sftp 端口
     */
    private int sftpPort;

    /**
      sftp 用户名
     */
    private String sftpUsername;

    /**
     * sftp 密码
     */
    private String sftpPassword;
}
