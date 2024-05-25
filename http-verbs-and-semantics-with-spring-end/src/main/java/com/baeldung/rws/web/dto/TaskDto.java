package com.baeldung.rws.web.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.baeldung.rws.domain.model.Campaign;
import com.baeldung.rws.domain.model.Task;
import com.baeldung.rws.domain.model.TaskStatus;

public record TaskDto( // @formatter:off
    Long id,

    String uuid,

    String name,

    String description,

    LocalDate dueDate,

    TaskStatus status,

    Long campaignId,

    WorkerDto assignee) { // @formatter:on

    public static class Mapper {
        public static Task toModel(TaskDto dto) {
            if (dto == null)
                return null;

            Campaign campaign = new Campaign();
            campaign.setId(dto.campaignId());

            Task model = new Task(dto.name(), dto.description(), dto.dueDate(), campaign, dto.status(), WorkerDto.Mapper.toModel(dto.assignee()), dto.uuid());
            if (!Objects.isNull(dto.id())) {
                model.setId(dto.id());
            }

            // we won't allow creating or modifying Campaigns via a Task
            return model;
        }

        public static TaskDto toDto(Task model) {
            if (model == null)
                return null;
            TaskDto dto = new TaskDto(model.getId(), model.getUuid(), model.getName(), model.getDescription(), model.getDueDate(), model.getStatus(), model.getCampaign()
                .getId(), WorkerDto.Mapper.toDto(model.getAssignee()));
            return dto;
        }
    }
}
