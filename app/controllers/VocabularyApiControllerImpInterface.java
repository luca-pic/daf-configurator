package controllers;

import apiModels.Vocabulary;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


@SuppressWarnings("RedundantThrows")
public interface VocabularyApiControllerImpInterface {
    void addVoc(Vocabulary body) throws Exception;

    void deleteVoc(Long vocId) throws Exception;

    Vocabulary getVocById(Long vocId, String authorization) throws Exception;

    void updateVoc(Vocabulary body) throws Exception;

    void updateVocWithForm(Long vocId, String name) throws Exception;

}
