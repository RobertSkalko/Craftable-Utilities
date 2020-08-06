package com.robertx22.craftable_utilities.main;

import io.github.prospector.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public String getModId() {
        return Ref.ID;
    }
}
