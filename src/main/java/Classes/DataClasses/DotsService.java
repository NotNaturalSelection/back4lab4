package Classes.DataClasses;

import Classes.SpringRepository.DotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DotsService {

    DotRepository dotRepository;

    @Autowired
    public DotsService(DotRepository dotRepository) {
        this.dotRepository = dotRepository;
    }

    public List<Dot> getDotsByUsername(String username){
        return dotRepository.findAllByOwner(username);
    }

    public void saveDot(Dot dot) {
        dot.setHit(isDotHits(dot));
        dotRepository.save(dot);
    }

    public boolean isDotHits(Dot dot) {
        return (dot.getX() * dot.getX() + dot.getY() * dot.getY() <= (dot.getR() / 2) * (dot.getR() / 2) ||
                (dot.getX() >= -dot.getR() / 2 && dot.getX() <= 0 && dot.getY() <= 0 && dot.getY() >= -dot.getR()) ||
                (dot.getX() >= 0 && dot.getY() >= 0 && dot.getY() + dot.getX() <= dot.getR() / 2));
    }
}
