#version 450 core
precision highp float;
precision highp int;


void thing() {
}

void with_diagnostic() {
}

void wgsl_main() {
    thing();
    with_diagnostic();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
