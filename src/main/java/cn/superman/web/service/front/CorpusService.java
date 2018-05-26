package cn.superman.web.service.front;

import cn.superman.web.dao.CorpusDao;
import cn.superman.web.dto.Corpus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpusService {
    @Autowired
    CorpusDao corpusDao;
    public List<Corpus> getCorpus(){
        List<Corpus> corpus = corpusDao.getCorpus();
        return corpus;
    }
}
