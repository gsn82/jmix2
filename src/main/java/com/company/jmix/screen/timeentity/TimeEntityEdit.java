package com.company.jmix.screen.timeentity;

import io.jmix.ui.screen.*;
import com.company.jmix.entity.TimeEntity;

@UiController("TimeEntity.edit")
@UiDescriptor("time-entity-edit.xml")
@EditedEntityContainer("timeEntityDc")
public class TimeEntityEdit extends StandardEditor<TimeEntity> {
}