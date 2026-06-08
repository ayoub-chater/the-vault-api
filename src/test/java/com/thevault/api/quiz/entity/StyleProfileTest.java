package com.thevault.api.quiz.entity;

import com.thevault.api.quiz.enums.BodyShape;
import com.thevault.api.quiz.enums.SkinToneGroup;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StyleProfileTest {

    @Test
    void shouldHaveCorrectDefaultValues() {
        StyleProfile profile = StyleProfile.builder()
                .userId(1L)
                .build();

        assertThat(profile.isCompleted()).isFalse();
        assertThat(profile.getCompletedAt()).isNull();
    }

    @Test
    void shouldStoreBodyShapeAndSkinTone() {
        StyleProfile profile = StyleProfile.builder()
                .userId(1L)
                .bodyShape(BodyShape.HOURGLASS)
                .skinToneGroup(SkinToneGroup.NEUTRO)
                .build();

        assertThat(profile.getBodyShape()).isEqualTo(BodyShape.HOURGLASS);
        assertThat(profile.getSkinToneGroup()).isEqualTo(SkinToneGroup.NEUTRO);
    }
}
