package min.jun.hippo4j.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResult<T> {
    private long totalElements;
    private List<T> content = new ArrayList<>();
    public PageResult() {

    }
    public PageResult(Page<T> page) {
        this.totalElements = page.getTotal();
        this.content = page.getRecords();
    }

}
