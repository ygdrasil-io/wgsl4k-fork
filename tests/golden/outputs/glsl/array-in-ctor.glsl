#version 450 core
precision highp float;
precision highp int;

struct Struct_2 {
    float[2] inner;
};
layout(set = 0, binding = 0) buffer Struct_2 global_0;

void wgsl_cs_main() {
    Struct_2 ah = global_0;
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_cs_main();
}
