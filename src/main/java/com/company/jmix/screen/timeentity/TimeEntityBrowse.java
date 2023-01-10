package com.company.jmix.screen.timeentity;

import io.jmix.ui.screen.*;
import com.company.jmix.entity.TimeEntity;

@UiController("TimeEntity.browse")
@UiDescriptor("time-entity-browse.xml")
@LookupComponent("timeEntitiesTable")
public class TimeEntityBrowse extends StandardLookup<TimeEntity> {
}