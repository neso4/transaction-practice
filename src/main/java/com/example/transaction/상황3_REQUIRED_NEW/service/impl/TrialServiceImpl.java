package com.example.transaction.상황3_REQUIRED_NEW.service.impl;

import com.example.transaction.상황3_REQUIRED_NEW.dao.TrialDao;
import com.example.transaction.상황3_REQUIRED_NEW.service.TrialService;
import com.example.transaction.상황3_REQUIRED_NEW.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;

    @Override
    @Transactional
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        try {
            approvalService.approve(param.getApprovalVO());
        } catch (RuntimeException e) {
            log.info("예외 처리 코드");
        }
        return param;
    }

    @Override
    @Transactional
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        try {
            approvalService.approve(param.getApprovalVO());
        } catch (RuntimeException e) {
            log.info("예외 처리 코드");
        }
        return param;
    }
}
