#version 450 core
precision highp float;
precision highp int;


void wgsl_main() {
    vec3 a = cross(vec3(1.0f, 0.0f, 0.0f), vec3(0.0f, 1.0f, 0.0f));
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
