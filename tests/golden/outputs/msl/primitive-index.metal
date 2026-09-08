#include <metal_stdlib>
using namespace metal;

[[fragment]]
float4 func(uint index [[primitiveindex]]) {
    return float4(float(index), 1.0f, 1.0f, 1.0f);
}
