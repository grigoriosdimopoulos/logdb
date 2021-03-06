package com.logdb.mapper;

import com.logdb.dto.SessionDto;
import com.logdb.entity.Session;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SessionMapperImpl implements SessionMapper {
    @Override
    public Session convert(SessionDto sessionDto) {
        if(Objects.isNull(sessionDto)) {
            return null;
        }

        Session session = new Session();
        session.setUserAgent(sessionDto.getBrowser());
        session.setId(sessionDto.getId());
        session.setSourceIp(sessionDto.getIp());
        return session;
    }

    @Override
    public SessionDto convert(Session session) {
        if(Objects.isNull(session)) {
            return null;
        }

        SessionDto sessionDto = new SessionDto();
        sessionDto.setBrowser(session.getUserAgent());
        sessionDto.setId(session.getId());
        sessionDto.setIp(session.getSourceIp());
        return sessionDto;
    }
}
