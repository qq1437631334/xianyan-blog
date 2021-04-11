package com.wsy.blog.constant;

/**
 * @author wsy
 * @date 2020-08-11 15:54
 */
public interface Constants {

    /**
     * 排序方式
     */
    String[] ORDER_BY_MODE = {"asc", "desc"};

    /**
     * 博客排序字段数组
     */
    String[] BLOG_ORDER_BY_FIELDS = {"blog_goods", "blog_read", "blog_type", "blog_comment", "blog_collection", "created_time", "update_time"};

    /**
     * 日志排序字段
     */
    String[] LOG_ORDER_BY_FIELDS = {"log_time", "created_time"};

    /**
     * 关于我的排序字段
     */
    String[] ABOUT_ORDER_BY_FIELDS = {"about_read", "update_time", "created_time"};

    /**
     * 音乐排序字段
     */
    String[] MUSIC_ORDER_BY_FIELDS = {"created_time", "artist", "enable"};


    /**
     * 请求日志分页查询url
     */
    String LOG_PAGE_URL = "/log/getPage";

    /**
     * 限定文件上传大小为10M以内
     */
    long FILE_SIZE_LIMIT = 1024 * 1024 * 10;

    /**
     * 博客默认封面图片
     */
    String DEFAULT_BLOG_IMAGE = "http://808km.top/blog/img/default.jpg";

    /**
     * 上传图片默认uri
     */
    String DEFAULT_IMG_UPLOAD_URI = "img/default/";
    /**
     * token头
     */
    String TOKEN = "token";

    /**
     * 英文符号
     */
    String SYMBOL_COMMA = ",";
    String SYMBOL_POINT = ".";
    String SYMBOL_QUESTION = "?";
    String SYMBOL_COLON = ":";
    String SYMBOL_STAR = "*";
    String SYMBOL_WELL = "#";
    String SYMBOL_HYPHEN = "-";
    String SYMBOL_UNDERLINE = "_";
    String SYMBOL_LEFT_BRACKET = "{";
    String SYMBOL_RIGHT_BRACKET = "}";
    String SYMBOL_RIGHT_EQUAL = "=";
    String SYMBOL_LEFT_OBLIQUE_LINE = "/";

    /**
     * 文件名后缀-img
     */
    String FILE_SUFFIX_WEBP = "webp";
    String FILE_SUFFIX_TIF = "tif";
    String FILE_SUFFIX_BMP = "bmp";
    String FILE_SUFFIX_GIF = "gif";
    String FILE_SUFFIX_JPG = "jpg";
    String FILE_SUFFIX_JPEG = "jpeg";
    String FILE_SUFFIX_PNG = "png";
    /**
     * 文件名后缀-doc
     */
    String FILE_SUFFIX_PPT = "ppt";
    String FILE_SUFFIX_XLS = "xls";
    String FILE_SUFFIX_PDF = "pdf";
    String FILE_SUFFIX_DOC = "doc";
    String FILE_SUFFIX_HLP = "hlp";
    String FILE_SUFFIX_WPS = "wps";
    String FILE_SUFFIX_RTF = "rtf";
    String FILE_SUFFIX_HTML = "html";
    String FILE_SUFFIX_MD = "md";
    String FILE_SUFFIX_DOCX = "docx";

    String FILE_SUFFIX_SQL = "sql";
    String FILE_SUFFIX_CSS = "css";
    String FILE_SUFFIX_JS = "js";
    String FILE_SUFFIX_VUE = "vue";
    String FILE_SUFFIX_JAVA = "java";

    String FILE_SUFFIX_RAR = "rar";
    String FILE_SUFFIX_ZIP = "zip";

    String FILE_SUFFIX_SVG = "svg";
    String FILE_SUFFIX_MSI = "msi";

    String FILE_SUFFIX_TXT = "txt";
    String FILE_SUFFIX_PPTX = "pptx";
    String FILE_SUFFIX_XLSX = "xlsx";
    String FILE_SUFFIX_RM = "rm";
    String FILE_SUFFIX_RMVB = "rmvb";
    String FILE_SUFFIX_WMV = "wmv";
    String FILE_SUFFIX_MP4 = "mp4";

    String FILE_SUFFIX_3GP = "3gp";
    String FILE_SUFFIX_MKV = "mkv";
    String FILE_SUFFIX_AVI = "avi";
    String FILE_SUFFIX_MPG = "mpg";
    String FILE_SUFFIX_MOV = "mov";
    String FILE_SUFFIX_SWF = "swf";

    String FILE_SUFFIX_WAV = "wav";

    String FILE_SUFFIX_AIF = "aif";
    String FILE_SUFFIX_AU = "au";
    String FILE_SUFFIX_MP3 = "mp3";
    String FILE_SUFFIX_RAM = "ram";
    String FILE_SUFFIX_WMA = "wma";
    String FILE_SUFFIX_MMF = "mmf";
    String FILE_SUFFIX_AMR = "amr";
    String FILE_SUFFIX_AAC = "aac";
    String FILE_SUFFIX_FLAC = "flac";


    /**
     * 数字 0~11
     */
    int MINUS_ONE = -1;
    int NUM_ZERO = 0;
    int NUM_ONE = 1;
    int NUM_TWO = 2;
    int NUM_THREE = 3;
    int NUM_FOUR = 4;
    int NUM_FIVE = 5;
    int NUM_SIX = 6;
    int NUM_SEVEN = 7;
    int NUM_EIGHT = 8;
    int NUM_NINE = 9;
    int NUM_TEN = 10;
    int NUM_ELEVEN = 11;
    int NUM_TWENTY = 20;
    int NUM_32 = 32;
    int NUM_1024 = 1024;
    int NUM_5000 = 5000;

    /**
     * 字符串数字 0~11
     */
    String STR_ZERO = "0";
    String STR_ONE = "1";
    String STR_TWO = "2";
    String STR_THREE = "3";
    String STR_FOUR = "4";
    String STR_FIVE = "5";
    String STR_SIX = "6";
    String STR_SEVEN = "7";
    String STR_EIGHT = "8";
    String STR_NINE = "9";
    String STR_TEN = "10";
    String STR_ELEVEN = "11";
    String STR_500 = "500";

    /**
     * 日期 + 时间
     */
    String DATE_FORMAT_YYYY = "yyyy";

    String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

}
