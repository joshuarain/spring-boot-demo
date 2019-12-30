package com.zhl.springboot.javamail.config;

/**
 * @author Lenovo
 * @title: EmailType
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/29 14:25
 */
public enum EmailType {
    EMAIL_TEXT_KEY("email_text_key", "文本邮件"),
    EMAIL_IMAGE_KEY("email_image_key", "图片邮件"),
    EMAIL_FILE_KEY("email_file_key", "文件邮件");
    private String code;
    private String value;
    EmailType(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public static String getByCode(String code) {
        EmailType[] values = EmailType.values();
        for (EmailType emailType: values) {
            if (emailType.code.equalsIgnoreCase(code)) {
                return emailType.value;
            }
        }
        return null;
    }
    // 省略 get set
}
