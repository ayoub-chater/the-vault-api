package com.thevault.api.zebra.service;

import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.zebra.dto.ZebraMatchResultDto;
import com.thevault.api.zebra.dto.ZebraSessionDto;

public interface ZebraMatchingService {

    ZebraSessionDto startSession();

    ZebraSessionDto submitAnswers(Long sessionId, QuizSubmitRequestDto request);

    ZebraMatchResultDto matchConsultant(Long sessionId);
}
