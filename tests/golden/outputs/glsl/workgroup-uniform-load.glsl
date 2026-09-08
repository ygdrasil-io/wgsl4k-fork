#version 450 core
precision highp float;
precision highp int;

 uint global_0 = 128u;
shared int[128] global_1;

void wgsl_test_workgroupUniformLoad(uvec3 workgroup_id) {
    int x = global_1[workgroup_id[0]];
    int val = workgroupUniformLoad(x);
    if ((val > 10)) {
        workgroupBarrier();
    }
}

int workgroupUniformLoad(int arg_0) {
}

void workgroupBarrier() {
}

layout(local_size_x = 4, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_workgroupUniformLoad(workgroup_id);
}
