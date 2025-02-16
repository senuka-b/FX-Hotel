package edu.icet.senuka.FXHotelManager.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SceneType {
    LOGIN("/view/login.fxml");

    private final String path;

    public String getPath() {
        return path;
    }
}
