package com.studentManager.common.Utils;

import lombok.Data;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;

@Data
public class PagingResult<T> {

    /**
     * 当前页
     */
    public int curPage;

    /**
     * 每页数量
     */
    public int pageSize;
    /**
     * 总数量
     */
    public int totalCount;
    /**
     * 总页数
     */
    public int totalPages;

    /**
     * 返回集合
     */
    public List<T> list;

    public PagingResult() {

    }

    public PagingResult(List<T> list) {
        PageInfo<T> ePageInfo = new PageInfo(list);
        this.curPage = ePageInfo.getPageNum();
        this.pageSize = ePageInfo.getPageSize();
        this.totalCount = (int) ePageInfo.getTotal();
        this.totalPages = ePageInfo.getPages();
        this.list = ePageInfo.getList();

        //清除上下文
        PageHelper.clearPage();
    }


}
