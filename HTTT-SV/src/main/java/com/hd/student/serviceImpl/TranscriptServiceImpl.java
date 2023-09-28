package com.hd.student.serviceImpl;

import com.hd.student.entity.OnlineService;
import com.hd.student.entity.Transcript;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.TranscriptRequest;
import com.hd.student.payload.response.TranscriptResponse;
import com.hd.student.repository.SemesterRepository;
import com.hd.student.repository.TranscriptRepository;
import com.hd.student.repository.UserRepository;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.TranscriptService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranscriptServiceImpl implements TranscriptService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IOnlineService onlineService;
    @Autowired
    private TranscriptRepository transcriptRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public ApiResponse addNewTranscript(TranscriptRequest rq, int userId) {

        Transcript tr = new Transcript();

        tr.setContactPhone(rq.getContactPhone());
        tr.setLanguage(rq.getLanguage());
        tr.setFromSemester(semesterRepository.findById(rq.getFromSemester()).orElseThrow(() -> new ResourceNotFoundException("Semester","Id",rq.getFromSemester())));
        tr.setToSemester(semesterRepository.findById(rq.getToSemester()).orElseThrow(() -> new ResourceNotFoundException("Semester","Id",rq.getToSemester())));
        tr.setQuantity(rq.getQuantity());
        tr.setIsSealed(rq.getIsSealed());

        OnlineService onlineService = this.onlineService.addOnlineService(userId,1);


        tr.setOnlineService(onlineService);
        this.transcriptRepository.save(tr);
        return new ApiResponse("Success", true);
    }

    @Override
    public TranscriptResponse findByOnlineServiceId(int id, int userId) {
        OnlineService on = this.onlineService.findById(id, userId);

        Transcript tr = on.getTranscript();
        if(tr == null)
            throw new ResourceNotFoundException("Không tìm thấy yêu cầu của bạn", "id", on.getId());
        return modelMapper.map(tr, TranscriptResponse.class);
    }
}
