package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统上传文件表
 * </p>
 *
 * @author youwk
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysFile implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 文件编号
     */
    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;

    /**
     * 上传源文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 所属对象编号
     */
    private Integer belongId;

    /**
     *
     */
    private Integer belongObj;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 上传用户编号
     */
    private Long uploadUserId;

    /**
     * 文件描述
     */
    private String fileDesc;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 删除状态（0-未删除，1-已删除）
     */
    private Integer isDel;

    /**
     * 文件服务器信息
     */
    private String fileServer;

    public static final String FILE_ID = "file_id";

    public static final String FILE_NAME = "file_name";

    public static final String FILE_PATH = "file_path";

    public static final String FILE_TYPE = "file_type";

    public static final String BELONG_ID = "belong_id";

    public static final String BELONG_OBJ = "belong_obj";

    public static final String UPLOAD_TIME = "upload_time";

    public static final String UPLOAD_USER_ID = "upload_user_id";

    public static final String FILE_DESC = "file_desc";

    public static final String FILE_SIZE = "file_size";

    public static final String IS_DEL = "is_del";

    public static final String FILE_SERVER = "file_server";

}
