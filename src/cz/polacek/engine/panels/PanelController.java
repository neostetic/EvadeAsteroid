package cz.polacek.engine.panels;

public enum PanelController {
    ARCADE(
        "%key",
        "%key",
        "%dpad_up,%dpad_down,%dpad_left,%dpad_right",
        "%key"
    ),
    COMPUTER(
        "%key_enter",
        "%key_spacebar,%key_enter",
        "%key_w,%key_s,%key_a,%key_d",
        "%key_spacebar"
    ),
    PC(COMPUTER.enter, COMPUTER.shoot, COMPUTER.move, COMPUTER.back),
    XBOX(
        "%xbox_a",
        "%xbox_a,%xbox_b,%xbox_x,%xbox_y",
        "%l_stick_up,%l_stick_down,%l_stick_left,%l_stick_right or %dpad_up,%dpad_down,%dpad_left,%dpad_right",
        "%xbox_b"
    ),
    XBOX_COLOR(
        "%xbox_a_col",
        "%xbox_a_col,%xbox_b_col,%xbox_x_col,%xbox_y_col",
        "%l_stick_up,%l_stick_down,%l_stick_left,%l_stick_right or %dpad_up,%dpad_down,%dpad_left,%dpad_right",
        "%xbox_b_col"
    ),
    PS(
        "%ps_x",
        "%ps_x,%ps_o,%ps_sq,%ps_tr",
        "%l_stick_up,%l_stick_down,%l_stick_left,%l_stick_right or %dpad_up,%dpad_down,%dpad_left,%dpad_right",
        "%ps_o"
    ),
    PS_COLOR(
        "%ps_x_col",
        "%ps_x_col,%ps_o_col,%ps_sq_col,%ps_tr_col",
        "%l_stick_up,%l_stick_down,%l_stick_left,%l_stick_right or %dpad_up,%dpad_down,%dpad_left,%dpad_right",
        "%ps_o_col"
    );

    public final String shoot;
    public final String enter;
    public final String move;
    public final String back;

    PanelController(String enter, String shoot, String move, String back) {
        this.shoot = shoot;
        this.enter = enter;
        this.move = move;
        this.back = back;
    }
}
