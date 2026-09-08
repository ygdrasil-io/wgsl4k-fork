#version 450 core
precision highp float;
precision highp int;


void wgsl_comp(uvec3 id) {
    if ((id[0] == 0)) {
    }
    (1 + 1);
    return;
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_comp(id);
}
