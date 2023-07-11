package com.haibao.sftp.pool.config;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPool;

/*
 * @Author ml.c
 * @Description //sftp动态连接池
 * @Date 5:45 PM 1/14/21
 **/
@Data
public class SftpPool{

    private GenericObjectPool<ChannelSftp> internalPool;

    public SftpPool(SftpFactory factory) {
        initPool(factory);
    }

    /**
     * 初始化
     * @param factory
     */
    private void initPool(SftpFactory factory) {

        SftpAbandonedConfig sftpAbandonedConfig = new SftpAbandonedConfig.Builder()
                //如果设置为true，就意味着，在borrowObject方法中（从对象池中申请对象的时候）就可以进行遗弃对象的相关清理逻辑。 * （当然是否能触发清理，还受限于其他条件）
                .removeAbandonedOnBorrow(true)
                //对象池本省可以通过GenericObjectPoolConfig配置后台异步清理任务，但是后台清理任务的主要职责是关注空闲（状态为空闲）对象的检测和清理。 * 如果removeAbandonedOnMaintenance设置为true，就意味着，在对象池的异步清理任务中，也可以进行遗弃（状态为活跃）对象的相关清理。
                .removeAbandonedOnMaintenance(true)
                .requireFullStackTrace(true)
                //这个时间，默认300s。如果对象池的一个对象被占用了超过300s还没有被释放，就认为是被调用方遗弃了。
                .removeAbandonedTimeout(60*10)
                //如果处理发了遗弃对象的回收和清理，是否要打印该对象的调用堆栈。 * 生产环境十分不建议设为true，会很消耗性能
                .logAbandoned(false)
                .useUsageTracking(false)
                .build();

        internalPool = new GenericObjectPool<>(factory, factory.getProperties().getPool(),sftpAbandonedConfig);
    }

    /**
     * 获取一个sftp连接对象
     * @return sftp连接对象
     */
    public ChannelSftp borrowObject() throws Exception {
            return internalPool.borrowObject();
    }

    /**
     * 归还一个sftp连接对象
     * @param channelSftp sftp连接对象
     */
    public void returnObject(ChannelSftp channelSftp) {

        if (null != channelSftp) {
            internalPool.returnObject(channelSftp);
        }
    }

}
