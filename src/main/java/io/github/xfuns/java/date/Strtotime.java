package io.github.xfuns.java.date;

import io.github.xfuns.java.Fun;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现类似 PHP strtotime() 部分功能，尝试解析时间字符串，返回本地时间戳
 *
 * @author smallmenu
 */
public final class Strtotime {
    private static final List<DMatcher> DT_MATCHERS;

    private static final List<DMatcher> D_MATCHERS;

    public interface DMatcher {
        /**
         * @param input 时间转换表达式
         * @param time  时间戳
         * @return LocalDateTime
         */
        LocalDateTime tryConvert(String input, Long time);
    }

    static {
        D_MATCHERS = new LinkedList<>();
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d")));
    }

    static {
        DT_MATCHERS = new LinkedList<>();

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH点mm分ss秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d HH:m:s")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH时m分s秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH点m分s秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH:m:s")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d HH:m:s")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d H:m:s")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 H时m分s秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 H点m分s秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 H:m:s")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d H:m:s")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'z'")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ssX")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ssz")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss'z'")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'z'")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSSX")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSSz")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSS'z'")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSS")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d HH:m")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d H:m")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d HH:m")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d H:m")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH点mm分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH时m分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 H时m分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH点m分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 H点m分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH:m")));

        DT_MATCHERS.add(new DMatcher() {
            private final Pattern regex = Pattern.compile("[\\-+]?\\d+ (year|month|day|hour|minute|second|week)[s]?");

            @Override
            public LocalDateTime tryConvert(String input, Long time) {
                String exp = Fun.trim(input);
                Matcher matcher = regex.matcher(exp);
                if (!Fun.blank(exp) && matcher.find()) {
                    int t = Fun.toInt(exp.split(" ")[0]);
                    LocalDateTime ldt = LocalDateTime.now();
                    if (time != null) {
                        ldt = LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(time)), ZoneId.systemDefault());
                    }

                    if (t != 0) {
                        String type = matcher.group(1);
                        switch (type) {
                            case "year":
                                ldt = ldt.plusYears(t);
                                break;
                            case "month":
                                ldt = ldt.plusMonths(t);
                                break;
                            case "day":
                                ldt = ldt.plusDays(t);
                                break;
                            case "hour":
                                ldt = ldt.plusHours(t);
                                break;
                            case "minute":
                                ldt = ldt.plusMinutes(t);
                                break;
                            case "second":
                                ldt = ldt.plusSeconds(t);
                                break;
                            case "week":
                                ldt = ldt.plusWeeks(t);
                                break;
                            default:
                        }
                    }

                    return ldt;
                }

                return null;
            }
        });
    }

    private static class DateTimeFormatMatcher implements DMatcher {

        private final DateTimeFormatter dateFormat;

        DateTimeFormatMatcher(DateTimeFormatter dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public LocalDateTime tryConvert(String input, Long time) {
            try {
                return LocalDateTime.parse(input, this.dateFormat);
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }

    private static class DateFormatMatcher implements DMatcher {

        private final DateTimeFormatter dateFormat;

        DateFormatMatcher(DateTimeFormatter dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public LocalDateTime tryConvert(String input, Long time) {
            try {
                LocalDate ld = LocalDate.parse(input, this.dateFormat);
                return LocalDateTime.of(ld, LocalTime.MIN);
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }

    public static long parse(String input) {
        if (input != null) {
            LocalDateTime ldt;
            for (DMatcher matcher : DT_MATCHERS) {
                ldt = matcher.tryConvert(input, null);
                if (ldt != null) {
                    // 如果包含 UTC 时间属性，或带有时区则进行转换
                    if (Fun.contains(input, "T")) {
                        int utcHour = getUtcHour(input);
                        return ldt.toEpochSecond(ZoneOffset.ofHours(utcHour));
                    }
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }

            for (DMatcher matcher : D_MATCHERS) {
                ldt = matcher.tryConvert(input, null);
                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }
        }
        return 0L;
    }

    public static long parse(String input, Long time) {
        if (input != null) {
            LocalDateTime ldt;
            for (DMatcher matcher : DT_MATCHERS) {
                ldt = matcher.tryConvert(input, time);

                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }

            for (DMatcher matcher : D_MATCHERS) {
                ldt = matcher.tryConvert(input, time);
                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }
        }

        return 0L;
    }

    private static int getUtcHour(String input) {
        List<String> matches = Fun.regexMatch("(([\\+-]\\d{2})[:]?\\d{2})", input);

        if (matches.size() == 3) {
            int hour = Fun.toInt(matches.get(2));
            if (hour > -24 && hour < 24) {
                return hour;
            }
        }

        return 0;
    }
}
