package controllers;

import apiModels.Vocabulary;
import apiModels.Error;
import com.google.errorprone.annotations.RestrictedApi;
import it.gov.daf.helpers.TestJava;

import javassist.NotFoundException;
import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
import javax.xml.ws.http.HTTPException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-08-31T11:37:42.017+02:00")

public class VocabularyApiControllerImp implements VocabularyApiControllerImpInterface {
    @Override
    public void addVoc(Vocabulary body) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void deleteVoc(Long vocId) throws Exception {
        //Do your magic!!!
    }

    @Override
    public Vocabulary getVocById(Long vocId) throws Exception {
        //Do your magic!!!
        //return new Vocabulary();
        return TestJava.test();
    }

    @Override
    public void updateVoc(Vocabulary body) throws Exception {
        //Do your magic!!!
    }

    @Override
    public void updateVocWithForm(Long vocId, String name) throws Exception {
        //Do your magic!!!
    }

}
