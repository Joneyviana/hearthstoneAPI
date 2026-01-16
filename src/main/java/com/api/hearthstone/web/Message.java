package com.api.hearthstone.web;

import jakarta.validation.constraints.NotBlank;

public record Message(@NotBlank(message = "Content is mandatory")
                      String content){
}
