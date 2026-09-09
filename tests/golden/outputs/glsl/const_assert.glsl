#version 450 core
precision highp float;
precision highp int;

 int global_0 = 1;
 int global_1 = 2;
 bool global_2 = false;

void wgsl_foo() {
    int z = ((global_0 + global_1) - 2);
    bool l_false = false;
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_foo();
}
