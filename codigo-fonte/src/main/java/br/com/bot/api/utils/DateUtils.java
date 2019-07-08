package br.com.bot.api.utils;

import br.com.bot.api.exceptions.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static Date format(String date) throws BusinessException {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
            return sdf.parse(date.replaceAll("Z", ""));
        }catch (ParseException b){
            throw new BusinessException(ConstantsUtils.MSG_ERROR_INVALID_DATE);
        }
    }

}
