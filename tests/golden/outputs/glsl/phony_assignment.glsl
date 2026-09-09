#version 450 core
precision highp float;
precision highp int;

layout(set = 0, binding = 0) uniform float global_0;

int five() {
    return 5;
}

void wgsl_main(uvec3 id) {
    global_0;
    global_0;
    int a = 5;
    a;
    five();
    int b = five();
    float phony = global_0;
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main(id);
}
