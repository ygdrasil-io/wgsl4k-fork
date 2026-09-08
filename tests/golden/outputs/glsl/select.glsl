#version 450 core
precision highp float;
precision highp int;


void wgsl_main() {
    select(1, 2.0f, false);
    vec2 x0 = vec2(1, 2);
    vec2 i1 = select(vec2(1.0f, 0.0f), vec2(0.0f, 1.0f), (x0[0] < x0[1]));
}

int select(int arg_0, float arg_1, bool arg_2) {
}

vec2 select(vec2 arg_0, vec2 arg_1, bool arg_2) {
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
