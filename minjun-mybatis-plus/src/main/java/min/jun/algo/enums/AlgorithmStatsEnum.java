package min.jun.algo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
//@RequiredArgsConstructor
@AllArgsConstructor
public enum AlgorithmStatsEnum {

    ZERO(0, "未上架"),
    ONE(1, "已上架");

    private final int code;
    private final String msg;

    public static final List<Integer> execStatsList = Stream.of(AlgorithmStatsEnum.ONE.getCode(), AlgorithmStatsEnum.ZERO.getCode()).collect(Collectors.toList());
}
