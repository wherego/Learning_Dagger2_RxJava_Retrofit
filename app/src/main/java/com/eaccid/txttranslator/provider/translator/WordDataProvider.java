package com.eaccid.txttranslator.provider.translator;

import com.eaccid.txttranslator.model.translator.translator.TextTranslation;
import com.eaccid.txttranslator.model.translator.lingualeo_impl.translator.TranslatorFactory;
import com.eaccid.txttranslator.model.translator.lingualeo_impl.translator.Translators;
import com.eaccid.txttranslator.model.translator.translator.TranslatorRx;
import com.eaccid.txttranslator.provider.fromtext.WordFromText;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

public class WordDataProvider implements DataProvider {

    @Override
    public Observable<List<String>> procureTranslationsObservable(WordFromText wordFromText) {
        TranslatorRx translator = TranslatorFactory.newRxTranslator(Translators.YANDEX_TRANSLATOR);
        return translator.translateObservable(wordFromText.getText()).flatMap(new Func1<TextTranslation, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call(TextTranslation textTranslation) {
                return Observable.just(textTranslation.getTranslates());
            }
        });
    }
}
