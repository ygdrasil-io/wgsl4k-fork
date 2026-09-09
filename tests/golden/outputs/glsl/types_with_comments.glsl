#version 450 core
precision highp float;
precision highp int;

struct Struct_1 {
    uint test_m;
};
layout(set = 0, binding = 0) uniform mat4x4 global_0;
shared mat2x2 global_1;
shared mat2x2 global_2;
 uint global_3 = 1;

void test_f() {
}

void test_g() {
}

void wgsl_test_ep() {
    global_2;
    Struct_1();
    test_g();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_ep();
}
