package example.service.teacher;

import example.dao.TraningproRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraningproService {
    final
    private TraningproRepository traningproDao;
    @Autowired
    public TraningproService(TraningproRepository traningproDao) {
        this.traningproDao = traningproDao;
    }
    public String findTypeByTraningproId(Integer traningproId){
        return traningproDao.findTypeByTraningproId(traningproId);
    }
}
