package com.hd.student.serviceImpl;

import com.hd.student.entity.OnlineService;
import com.hd.student.entity.Transcript;
import com.hd.student.entity.User;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.ApiResponse;
import com.hd.student.payload.TranscriptRequest;
import com.hd.student.repository.SemeterRepository;
import com.hd.student.repository.TranscriptRepository;
import com.hd.student.repository.UserRepository;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.TranscriptService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TranscriptServiceImpl implements TranscriptService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IOnlineService onlineService;
    @Autowired
    private TranscriptRepository transcriptRepository;
    @Autowired
    private SemeterRepository semeterRepository;


    @Override
    @Transactional
    public ApiResponse addNewTranscript(TranscriptRequest rq, int userId) {

        Transcript tr = new Transcript();

        tr.setContactPhone(rq.getContactPhone());
        tr.setLanguage(rq.getLanguage());
        tr.setFromSemeter(semeterRepository.findById(rq.getFromSemeter()).orElseThrow(() -> new ResourceNotFoundException("Semeter","Id",rq.getFromSemeter())));
        tr.setToSemeter(semeterRepository.findById(rq.getToSemeter()).orElseThrow(() -> new ResourceNotFoundException("Semeter","Id",rq.getToSemeter())));
        tr.setQuantity(rq.getQuantity());
        tr.setIsSealed(rq.getIsSealed());

        OnlineService onlineService = this.onlineService.addOnlineService(userId,1);


        tr.setOnlineService(onlineService);
        this.transcriptRepository.save(tr);
        return new ApiResponse("Success", true);

    }
}
