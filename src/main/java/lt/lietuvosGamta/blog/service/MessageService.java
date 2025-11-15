package lt.lietuvosGamta.blog.service;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageService {

    private final MessageSource messageSource;


    public String translate(String key) {
        if (key == null) {
            return null;
        }
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

}