#version 450 core
precision highp float;
precision highp int;

struct Struct_6 {
    uint[512] arr;
    int atom;
    int[8][8] atom_arr;
};
layout(set = 0, binding = 0) buffer uint[512] global_0;
shared Struct_6 global_1;

void wgsl_main() {
    global_0 = global_1.arr;
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
