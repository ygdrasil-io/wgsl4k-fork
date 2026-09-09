#version 450 core
precision highp float;
precision highp int;

struct Struct_3 {
    int a;
    mat2x2 b;
};
layout(set = 0, binding = 0) uniform Struct_3 global_0;

void wgsl_main() {
    Struct_3 v = global_0;
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
