#version 450 core
precision highp float;
precision highp int;


void break_from_loop() {
    {
        int i = 0;
        while (true) {
            if ((i < 4)) {
                {
                    break;
                }
                i = 1;
            } else {
                break;
            }
        }
    }
}

void wgsl_main() {
    break_from_loop();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
