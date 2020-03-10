package com.moises.data.skillset.mapper

import com.moises.data.core.mapper.Transformer
import com.moises.data.skillset.model.SkillItem
import com.moises.domain.skillset.model.Skill

class SkillItemToSkill: Transformer<SkillItem, Skill>() {
    override fun transform(value: SkillItem): Skill {
        return Skill(name = value.name.orEmpty(), percentage = value.percentage ?: 0.0)
    }
}