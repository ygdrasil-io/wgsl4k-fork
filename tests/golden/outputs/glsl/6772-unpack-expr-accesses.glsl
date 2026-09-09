#version 450 core
precision highp float;
precision highp int;


void wgsl_main() {
    int idx = 2;
    unpack4xI8(12u)[idx];
    unpack4xU8(12u)[1];
}

uint unpack4xI8(uint arg_0) {
}

uint unpack4xU8(uint arg_0) {
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
