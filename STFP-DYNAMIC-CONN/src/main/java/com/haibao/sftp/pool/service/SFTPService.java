package com.haibao.sftp.pool.service;

import com.haibao.sftp.pojo.ParmsQuery;
import org.springframework.http.ResponseEntity;

/**
 * 公共sftp接口
 */
public interface SFTPService {

    ResponseEntity getSftpPicStream(ParmsQuery parmsQuery);
}
