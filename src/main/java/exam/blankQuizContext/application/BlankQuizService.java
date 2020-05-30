package exam.blankQuizContext.application;

import exam.blankQuizContext.domain.model.blankquiz.BlankQuiz;
import exam.blankQuizContext.domain.model.blankquiz.BlankQuizId;
import exam.blankQuizContext.domain.model.blankquiz.BlankQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlankQuizService {

    private BlankQuizRepository blankQuizRepository;

    @Autowired
    public BlankQuizService(BlankQuizRepository blankQuizRepository) {
        this.blankQuizRepository = blankQuizRepository;
    }

    public List<BlankQuiz> getAll() {
        return blankQuizRepository.getAll();
    }

    public BlankQuiz find(String blankQuizId) {
        return blankQuizRepository.find(new BlankQuizId(blankQuizId));
    }

    public void createBlankQuiz(CreateBlankQuizCommand command) {
        BlankQuizId blankQuizId = blankQuizRepository.nextBlankQuizId();
        BlankQuiz blankQuiz = new BlankQuiz(blankQuizId, command.getQuestion(), command.getAnswer());
        blankQuizRepository.save(blankQuiz);
    }

    public void updateBlankQuiz(String blankQuizId, UpdateBlankQuizCommand command) {
        BlankQuiz blankQuiz = blankQuizRepository.find(new BlankQuizId(blankQuizId));
        blankQuiz.update(command.getQuestion(), command.getAnswer());
    }

    public void deleteBlankQuiz(String blankQuizId) {
        blankQuizRepository.delete(new BlankQuizId(blankQuizId));
    }
}
