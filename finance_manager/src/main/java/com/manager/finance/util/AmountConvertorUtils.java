/**
 * @Autor GapSerg
 * @Create 2022-08-24.08.2022 12:42
 **/

package com.manager.finance.util;

import java.util.Formatter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AmountConvertorUtils {

    public static Integer stringDoubleToInt(String amount) {
        return (int) Math.round(Double.parseDouble(amount) * 100);
    }

    public static String intToStringDouble(Integer amount) {
        return new Formatter().format("%.2f", ((double) (amount)) / 100).toString();
    }
}
