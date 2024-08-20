package com.studentManager.common.Entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("files")
public class ResourcesFileEntry {
    private String id;

    private String localUrl;

    private int onShelf;

    private Long size;

    private String mediaId;

    private int type;
}
