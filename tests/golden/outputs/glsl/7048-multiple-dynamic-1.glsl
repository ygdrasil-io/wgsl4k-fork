#version 450 core
precision highp float;
precision highp int;


void wgsl_f() {
    vec3[2] b = vec3[2]();
    vec4 poly = vec4(0);
    int k = 0;
    int j = 0;
    poly[0] = (b[j][1] * b[k][2]);
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_f();
}
