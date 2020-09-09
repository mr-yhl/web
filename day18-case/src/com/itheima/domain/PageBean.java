package com.itheima.domain;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<E> {
    private  Integer totalCount;
    private  Integer totalPage;
    private List<E> list;
    private  Integer pageNum;
    private  Integer pageSize;
}
