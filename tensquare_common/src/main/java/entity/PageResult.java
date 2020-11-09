package entity;

import java.util.List;

/**
 * 分页结果类
 * @param <T>
 *
 * @author semiercq
 * @date 2020/11/08
 **/
public class PageResult<T> {
    /**
     * TODO
     */
    private Long total;

    /**
     * TODO
     */
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
