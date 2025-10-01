package com.umg.service;

import com.umg.data.bo.TtLogs;

import java.util.List;
import java.util.Optional;

public interface TtLogsService {

    List<TtLogs> findAll();
    Optional<TtLogs> findById(Integer id);
    TtLogs update(TtLogs ttLogs);
    TtLogs deleteById(Integer id);
}
